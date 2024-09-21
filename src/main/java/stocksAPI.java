import org.jfree.data.xy.XYSeries;
import org.json.JSONArray;
import org.json.JSONObject ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class stocksAPI {
    double min = Double.MAX_VALUE;
    double max = Double.MIN_VALUE;
    double[] minMax ;
    private String apiKey = "9F5TBFFXTOVQ29TP" ;
    private String stockSymbol ;
    private JSONObject timeSeries  ;
    private String[] tickerSymbols ;

    stocksAPI(){

    }

    stocksAPI(String symbol){
        this.stockSymbol = symbol ;
        String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+stockSymbol+"&outputsize=full&apikey=" + apiKey ;

        try{
            // create a url with api endpoint and setup connection
            URL url = new URL(urlString);
            HttpURLConnection connection =  (HttpURLConnection) url.openConnection() ;
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                // read response from the input stream using stream reader and then convert into char using buffer reader
                BufferedReader data = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine ;
                StringBuilder response = new StringBuilder();
                while( (inputLine = data.readLine()) != null ){
                    response.append(inputLine);
                }
                data.close();

                // convert response to json object
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Now access the "Time Series (daily)" json object
                timeSeries = jsonResponse.getJSONObject("Time Series (Daily)");
            }else{
                System.out.println("Get request failed : Response code - " + responseCode);
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

    }

//    public JSONObject getJsonResponse() {
//        return jsonResponse;
//    }

    public String[] getSymbol(String keyword){
        String symbolString = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords="+keyword+"&apikey=" + apiKey ;

        try{
            URL urlSymbol = new URL(symbolString);
            HttpURLConnection symbolConnection = (HttpURLConnection) urlSymbol.openConnection();
            symbolConnection.setRequestMethod("GET");
            int responseCode2 = symbolConnection.getResponseCode();








            System.out.println("Response code : " );
            System.out.println(responseCode2);







            if(responseCode2 == HttpURLConnection.HTTP_OK){
                BufferedReader symbol = new BufferedReader(new InputStreamReader(symbolConnection.getInputStream()));
                //bestMatches
                String inputLine ;
                StringBuilder response = new StringBuilder();

                while( (inputLine = symbol.readLine()) != null){
                    response.append(inputLine) ;
                }
                symbol.close();

                JSONObject jsonResponse = new JSONObject(response.toString());


                System.out.println("Json response ");
                System.out.println(jsonResponse);





                JSONArray bestMatches = jsonResponse.getJSONArray("bestMatches") ;

                tickerSymbols = new String[bestMatches.length()] ;
                for(int i=0 ; i<bestMatches.length() ; i++){
                    JSONObject match = bestMatches.getJSONObject(i) ;
                    tickerSymbols[i] = match.getString("1. symbol");
                }

            }else{
                System.out.println("Get request failed : Response code - " + responseCode2);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return tickerSymbols ;
    }

    public double[] getCurrentData(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.of(2024,9,6);
        String date = currentDate.format(formatter);
        double price , volume ;

        if(timeSeries.has(date)){
            JSONObject currentData = timeSeries.getJSONObject(date);
            price = currentData.getDouble("4. close");
            volume = currentData.getDouble("5. volume");
        }else{
            price = 0;
            volume = 0 ;
        }

        return new double[] { price , volume} ;

    }


    public XYSeries getMonthlyData(){

        XYSeries series = new XYSeries(stockSymbol); // the name entered here will be assigned to the graph curve

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);

        for(LocalDate date = startDate ; !date.isAfter(endDate) ; date = date.plusDays(1)){
            String currentDate = date.format(formatter);
            double epochDate = date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000 ;

            if(timeSeries.has(currentDate)){

                JSONObject currentData = timeSeries.getJSONObject(currentDate);
                double price = currentData.getDouble("4. close");
                if (price < min) min = price;
                if (price > max) max = price;
                series.add(epochDate, price);
            }
        }

        return series ;

    }

    public XYSeries getYearlyData(){

        XYSeries series = new XYSeries(stockSymbol); // the name entered here will be assigned to the graph curve

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusYears(1);

        for(LocalDate date = startDate ; !date.isAfter(endDate) ; date = date.plusDays(1)){
            String currentDate = date.format(formatter);
            double epochDate = date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000 ;

            if(timeSeries.has(currentDate)){
                JSONObject currentData = timeSeries.getJSONObject(currentDate);
                double price = currentData.getDouble("4. close");
                if (price < min) min = price;
                if (price > max) max = price;
                series.add(epochDate, price);
            }
        }

        return series ;

    }

    public XYSeries getYearlyData5(){

        XYSeries series = new XYSeries(stockSymbol); // the name entered here will be assigned to the graph curve

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusYears(5);

        for(LocalDate date = startDate ; !date.isAfter(endDate) ; date = date.plusDays(1)){
            String currentDate = date.format(formatter);
            double epochDate = date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000 ;

            if(timeSeries.has(currentDate)){
                JSONObject currentData = timeSeries.getJSONObject(currentDate);
                double price = currentData.getDouble("4. close");
                if (price < min) min = price;
                if (price > max) max = price;
                series.add(epochDate, price);
            }
        }

        return series ;

    }

    public XYSeries getAllTimeData() {

        XYSeries series = new XYSeries(stockSymbol); // the name entered here will be assigned to the graph curve
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (String currentDate : timeSeries.keySet()) {

            LocalDate date = LocalDate.parse(currentDate, formatter);
            double epochDate = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            JSONObject currentData = timeSeries.getJSONObject(currentDate);
            double price = currentData.getDouble("4. close");
            if (price < min) min = price;
            if (price > max) max = price;
            series.add(epochDate, price);
        }

        return series;
    }

    public double[] getMinMaxValues(){

        return new double[]{min, max};
    }

    public static void main(String[] args) {

        new stocksAPI("RELIANCE.BSE") ;
    }


}
