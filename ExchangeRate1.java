import java.util.HashMap;
import java.util.Map;

public class ExchangeRate1 implements ConverterManager1 {
	private final Map<String, Map<String, Double>> exchanges = new HashMap<>();

    ExchangeRate1() {
        this.setupValues();
    }

    public void setupValues() {
        Map<String, Double> cop = new HashMap<>();
        cop.put("USD", 0.00021);
        cop.put("EUR", 0.00019);
        cop.put("MXN", 0.0038);
        cop.put("AED", 0.00076);
        cop.put("AUD", 0.00031);
        exchanges.put("COP", cop);

        Map<String, Double> usd = new HashMap<>();
        usd.put("COP", 4855.32);
        usd.put("EUR", 0.94);
        usd.put("MXN", 0.051);
        usd.put("AED", 3.67);
        usd.put("AUD", 1.49);
        exchanges.put("USD", usd);

        Map<String, Double> eur = new HashMap<>();
        eur.put("COP", 5138.29);
        eur.put("USD", 1.06);
        eur.put("MXN", 19.40);
        eur.put("AED", 3.88);
        eur.put("AUD", 1.57);
        exchanges.put("EUR", eur);

        Map<String, Double> mxn = new HashMap<>();
        mxn.put("COP", 264.06);
        mxn.put("USD", 0.054);
        mxn.put("EUR", 0.051);
        mxn.put("AED", 0.20);
        mxn.put("AUD", 0.081);
        exchanges.put("MXN", mxn);

        Map<String, Double> aed = new HashMap<>();
        aed.put("COP", 1321.86);
        aed.put("USD", 0.27);
        aed.put("EUR", 0.26);
        aed.put("MXN", 5.01);
        aed.put("AUD", 0.40);
        exchanges.put("AED", aed);
        
        Map<String, Double> aud = new HashMap<>();
        aud.put("COP", 3265.20);
        aud.put("USD", 0.67);
        aud.put("EUR", 0.64);
        aud.put("MXN", 12.37);
        aud.put("AED", 2.47);
        exchanges.put("AUD", aud);
    }

    public Double convert(String from, String to, int amount) {
        Map<String, Double> fromExchange = this.exchanges.get(from);

        return amount * fromExchange.get(to);
    }
}

