
import java.net.URI; 
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import java.util.Scanner;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;

import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.SMAIndicator;

import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.VolumeIndicator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
//sacar pullbacks?
public class Trigger {		//MAIN -->
		public static void main(String[] args) {	 	
													
													try {
														Start( );
													} catch (InterruptedException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
											//MAIN <--
		
		static void Start() throws InterruptedException  {
//==========================================================================================================================================//
		
		System.out.println(" Trigger comlementario, este script gatilla en histograma(macd,signal) ");
		
		
		System.out.println("Seleccionar Simbolo, BTCUSDT, BTCUSDC, PAXGUSDT..");
	    Scanner sc1 = new Scanner(System.in);
	    
	    String symbol =sc1.nextLine();
		
		
		System.out.println("Seleccionar Timeframe:1s,  1m, 5m, 15m, 30m, 1h, 1d ");
        Scanner sc = new Scanner(System.in);
        String interval =sc.nextLine(); 	
        
            
        System.out.println("Seleccionar cantidad de velas a analizar, 100, 200, etc (default200)");
        Scanner sc11 = new Scanner(System.in);
        int limit =sc11.nextInt() ;	
       
      
        String welcome ="Selecionar estado: 0) INIZIADO, 1) COMPRA_SIMULADA, 2) VENTA_SIMULADA, 3) COMPRA_ENVIADA,	4) VENTA_ENVIADA, 5) COMPRA_ABIERTA, 6) VENTA_ABIERTA, 7)COMPRA_CERRADA, 8)VENTA_CERRADA,9)COMPRA_FALSA, 10)VENTA_FALSA, 11)DETENIDO";
	    
		System.out.println(welcome);
	
	    State estado=State.DETENIDO;
		Scanner sc111 = new Scanner(System.in);
		int in=sc111.nextInt();
		
		if (in==0) {estado=State.INIZIADO;}
		if (in==1) {estado= State.COMPRA_SIMULADA;;}
		if (in==2) {;estado= State.VENTA_SIMULADA; }
		if (in==3) {;estado= State.COMPRA_ENVIADA; }
		if (in==4) {;estado= State.VENTA_ENVIADA;  }
		if (in==5) {;estado= State.COMPRA_ABIERTA; }
		if (in==6) {;estado= State.VENTA_ABIERTA;  }
		if (in==7) {;estado= State.COMPRA_CERRADA; }
		if (in==8) {;estado= State.VENTA_CERRADA;  }
		if (in==9) {;estado= State.COMPRA_FALSA;   }
		if (in==10){;estado= State.VENTA_FALSA;    }
		if (in==11){;estado= State.DETENIDO;       }
		sc1.close();	sc111.close();;sc.close();sc11.close();
        
//        																																	||
  	    		//int currentHour =0;
  	    		boolean tpOrderDone=false;					
  	    												
 	    		double  close=0.0;						double  entryPrice = 0.0;			  	
 	    		Target target =Target.DETENIDO;
 	    	    Mode mode =  Mode.DETENIDO;
 	    	    
 	    	   String url = String.format("https://api.binance.com/api/v3/klines?symbol=%s&interval=%s&limit=%d",symbol, interval, limit);
 	          
 	          HttpClient client = HttpClient.newHttpClient();
//==========================================================================================================================================// 	    
  	    
  	
        while (estado!= State.DETENIDO	) {						
            try {
            	
            	HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .version(HttpClient.Version.HTTP_1_1)
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); 
                
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootArray = mapper.readTree(response.body()); 
                
              
                	BaseBarSeries seriesM = new BaseBarSeries(symbol);	
                	
               	   for (int i=0;i<limit; i++) 
             			       {	   
             			    		   addCandleToSeries(rootArray,seriesM,  i);
             			    	   } 
               	   
               	   
               	   ClosePriceIndicator closeprice = new ClosePriceIndicator(seriesM);
               	   							

               	   MACDIndicator macd = new MACDIndicator(closeprice, 12, 26);   
               	  EMAIndicator signal = new EMAIndicator(macd,9);
               	  
               	   close  = seriesM.getLastBar().getClosePrice().doubleValue(); 
               	   String closeTxt  =" "+FormatoDeNumero(close)+" "; 

               	boolean bull=	seriesM.getLastBar().isBullish();
               	boolean bear= 	seriesM.getLastBar().isBearish();
               	
               	boolean readFileBuy =Interact.Lector().contains("BUY");
               	boolean readFileSell =Interact.Lector().contains("SELL");
               	
                   double	curr	=	macd.getValue(limit-1).doubleValue();
            	   double	last	=	macd.getValue(limit-2).doubleValue();
            	   double	prev	=	macd.getValue(limit-3).doubleValue();
            	   
            	   boolean    isBuy		=	readFileBuy  &&	bull	&& 	curr	>	last	&&	last	>	prev	;
            	   boolean    isSell	=	readFileSell &&	bear	&&   curr	<	last	&&	last	<	prev	;
               	
              
     
         
              	if (estado!= State.DETENIDO ) {
    		 
    		 
												if (isBuy && estado != estado.COMPRA_SIMULADA && estado != estado.COMPRA_ABIERTA
														&& estado != estado.COMPRA_FALSA && estado != estado.COMPRA_CERRADA) {
													//clean
													
													if ( estado == estado.VENTA_ABIERTA	) {//compenzar aqui con op contraria 
														boolean queryTpOld 	=	false;
																
														
														Thread.sleep(3000);
														Interact.Message("Estaba el tpV en estado: "+queryTpOld);
														
														
														if(!queryTpOld) {
														

														
															 {
															        Interact.Message(interval+" Cerrada venta vieja, por q abre una nueva compra");
															        estado = estado.VENTA_CERRADA;
															        }
														}
			
													}
													
													//trade Compra
													
													if (estado != estado.INIZIADO && (  estado == estado.VENTA_CERRADA	||  estado == estado.VENTA_SIMULADA )) {
														
														if (estado != estado.COMPRA_ENVIADA) {
															
															tpOrderDone = false;boolean comprado=false; comprado =Buy(close);
															if (comprado) {
																target =Target.DETENIDO;
																estado = estado.COMPRA_ENVIADA;
																//Interact.Message(estado+" " + closeTxt);
																entryPrice = close;
															}
									
														}
									
														if (!tpOrderDone && estado == estado.COMPRA_ENVIADA) {
									
															
															tpOrderDone = true;
															
															estado = estado.COMPRA_ABIERTA;
															Interact.Message(interval+" "+estado+" :" + closeTxt+mode);
									
														}
									
													}
									
													if (estado == estado.INIZIADO) {
														estado = estado.COMPRA_SIMULADA;
														System.out.println(interval+" "+estado+" " + LocalDateTime.now());
														System.out.println(interval+" "+estado+" " + LocalDateTime.now());
														
													}
									
												} //FIN COMPRA====================
												if (isSell && estado != estado.VENTA_SIMULADA && estado != estado.VENTA_ABIERTA
														&& estado != estado.VENTA_FALSA && estado != estado.VENTA_CERRADA) {
													//clean
													
													if (estado == estado.COMPRA_ABIERTA) {
														boolean queryTpOld 	=	false;
														
																
															Thread.sleep(2000);
															Interact.Message(interval+" Estaba el tpB en estado: "+queryTpOld);
															Thread.sleep(1000);
															
															if(!queryTpOld) {
								
																	{
																		estado = estado.COMPRA_CERRADA;
																        Interact.Message(interval+" "+estado+" " +"Cerrada compra vieja, por q abre una nueva venta");
																   	 }
															}
														}
													//Trade Venta
													
													
														if (estado != estado.VENTA_ENVIADA&&( estado == estado.COMPRA_SIMULADA ||estado == estado.COMPRA_CERRADA) ) {
															
															if (estado != estado.INIZIADO) {	
															tpOrderDone = false;	boolean vendido=false; 	vendido=Sell(close);
															if (vendido )
																estado = estado.VENTA_ENVIADA;
															//Interact.Message("VENTA_ENVIADA: " + closeTxt);
															entryPrice = close;
														}
									
														if (!tpOrderDone && estado == estado.VENTA_ENVIADA)
									
														{
															
															tpOrderDone = true;
														
															estado = estado.VENTA_ABIERTA;
															Interact.Message(interval+" "+estado+" :" + closeTxt+mode);
														}
									
													}
									
													if (estado == estado.INIZIADO) {
														System.out.println(estado+" " + LocalDateTime.now());
														estado = estado.VENTA_SIMULADA;
													}
									
												}//FIN Venta====================
												//  TP			estado debug
												
												if (close < (entryPrice - 250) && !(entryPrice == 0) && estado == estado.VENTA_ABIERTA&&target ==Target.DETENIDO) {
													estado = estado.VENTA_CERRADA;target =Target.TAKE_PROFIT_1;
													Interact.Message(interval+" "+estado+" " + target +' ' + closeTxt);
												}
												if (close > (entryPrice + 250) && !(entryPrice == 0) && estado == estado.COMPRA_ABIERTA&&target ==Target.DETENIDO) {
													estado = estado.COMPRA_CERRADA;target =Target.TAKE_PROFIT_1;
													Interact.Message(interval+" "+estado+" " + target +' ' + closeTxt);
													
												}
												
												if (close < (entryPrice - 500) && !(entryPrice == 0) && estado == estado.VENTA_CERRADA&&target ==Target.TAKE_PROFIT_1) {
													target =Target.TAKE_PROFIT_2;
													Interact.Message(interval+" "+estado+" " + target +' ' + closeTxt);
												}
												if (close > (entryPrice + 500) && !(entryPrice == 0) && estado == estado.COMPRA_CERRADA&&target ==Target.TAKE_PROFIT_1) {
													target =Target.TAKE_PROFIT_2;
													Interact.Message(interval+" "+estado+" " + target +' ' + closeTxt);
													
												}
												
												if (close < (entryPrice - 750) && !(entryPrice == 0) && estado == estado.VENTA_CERRADA&&target ==Target.TAKE_PROFIT_2) {
													target =Target.TAKE_PROFIT_3;
													Interact.Message(interval+" "+estado+" " + closeTxt);
												}
												if (close > (entryPrice + 750) && !(entryPrice == 0) && estado == estado.COMPRA_CERRADA&&target ==Target.TAKE_PROFIT_2) {
													target =Target.TAKE_PROFIT_3;
													Interact.Message(interval+" "+estado+" " + target +' ' + closeTxt);
													
												}
												
												if (close < (entryPrice - 1000) && !(entryPrice == 0) && estado == estado.VENTA_CERRADA&&target ==Target.TAKE_PROFIT_3) {
													target =Target.TAKE_PROFIT_4;
													Interact.Message(estado+" " + target +' ' + closeTxt);
												}
												if (close > (entryPrice + 1000) && !(entryPrice == 0) && estado == estado.COMPRA_CERRADA&&target ==Target.TAKE_PROFIT_3) {
													target =Target.TAKE_PROFIT_4;
													Interact.Message(interval+" "+estado+" " + target +' ' + closeTxt);
													
												}
												
												if (close < (entryPrice - 1500) && !(entryPrice == 0) && estado == estado.VENTA_CERRADA&&target ==Target.TAKE_PROFIT_4) {
													target =Target.TAKE_PROFIT_5;
													Interact.Message(interval+" "+estado+" " + target +' ' + closeTxt);
												}
												if (close > (entryPrice + 1500) && !(entryPrice == 0) && estado == estado.COMPRA_CERRADA&&target ==Target.TAKE_PROFIT_4) {
													target =Target.TAKE_PROFIT_5;
													Interact.Message(interval+" "+estado+" " + target +' ' + closeTxt);
													
												}
										
		}
		Thread.sleep(600);
            } catch (Exception e) {
                e.printStackTrace();Interact.Message(interval+" "+"Fundio: "+close);
                break;
            }
            
        }
    }
    
  

	    
	    
		public static void addCandleToSeries(JsonNode rootArray, BarSeries series, int i) {
	        //  long openTimeMs = rootArray.get(i).get(0).asLong();
	          String open = rootArray.get(i).get(1).asText();
	          String high = rootArray.get(i).get(2).asText();
	          String low = rootArray.get(i).get(3).asText();
	          String close = rootArray.get(i).get(4).asText();
	          String volume = rootArray.get(i).get(5).asText();
	          long closeTimeMs = rootArray.get(i).get(0).asLong();

	          ZonedDateTime closeTime = Instant.ofEpochMilli(closeTimeMs).atZone(ZoneOffset.UTC);

	          BaseBar bar = new BaseBar(
	                  Duration.ofMinutes(15), //considerar a segundos
	                  closeTime,
	                  open,
	                  high,
	                  low,
	                  close,
	                  volume
	          							);

	          series.addBar(bar);
	          
	      }
    
    
	
   
		public static double FormatoDeNumero(Double numero) {
			BigDecimal decimal = new BigDecimal(numero);
			BigDecimal formateado=  decimal.setScale(0, RoundingMode.HALF_EVEN);//ver
			double forBinance = formateado.stripTrailingZeros().doubleValue();
			 return forBinance;
			}
	

	
	//Manipulacion de ordenes
	
	static boolean Buy(Double close) {	
	     
        return true;			
	}
	
	static boolean Sell(Double close) {
		
		
                
            return true;
	}
	
	

}

