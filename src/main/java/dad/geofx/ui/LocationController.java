package dad.geofx.ui;

import dad.geofx.api.GeoFXService;
import dad.geofx.api.model.Currency;
import dad.geofx.api.model.Ipapi;
import dad.geofx.api.model.Language;
import dad.geofx.api.model.TimeZone;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class LocationController implements Initializable {

    @FXML
    private Label callingcodeText;

    @FXML
    private Label cityText;

    @FXML
    private Label currencyText;

    @FXML
    private ImageView flagImage;

    @FXML
    private TextField ipText;

    @FXML
    private Label iplocationText;

    @FXML
    private Label languageText;

    @FXML
    private Label latitudeText;

    @FXML
    private Label longitudeText;

    @FXML
    private Label timezoneText;

    @FXML
    private BorderPane view;

    @FXML
    private Label zipText;

    private final GeoFXService geoFXService;

    public LocationController() {
        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ipapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear instancia de GeoFXService
        geoFXService = retrofit.create(GeoFXService.class);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LocationView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Hacer la llamada a la API para obtener la IP actual al iniciar la aplicación
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.ipify.org/")
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String ipAddress = response.body().string().trim();

                    // Actualizar el cuadro de texto en la interfaz de usuario
                    Platform.runLater(() -> ipText.setText(ipAddress));

                    // Luego, puedes realizar la llamada a la API GeoFX con la IP obtenida
                    getIpInfo(ipAddress);
                } else {
                    System.out.println("Error al obtener la dirección IP. Código: " + response.code());
                }
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.out.println("Error de red al obtener la dirección IP: " + e.getMessage());
            }
        });
    }

    public BorderPane getView() {
        return view;
    }

    @FXML
    void onCheckAction(ActionEvent event) {
        // Obtener la dirección IP del TextField
        String ipAddress = ipText.getText();

        // Hacer la llamada a la API
        Call<Ipapi> call = geoFXService.getIpInfo(ipAddress);
        call.enqueue(new Callback<Ipapi>() {
            @Override
            public void onResponse(Call<Ipapi> call, Response<Ipapi> response) {
                if (response.isSuccessful()) {
                    Ipapi ipInfo = response.body();
                    Platform.runLater(() -> updateLabels(ipInfo));
                } else {
                    System.out.println("Error al obtener información de IP. Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Ipapi> call, Throwable t) {
                System.out.println("Error de red: " + t.getMessage());
            }
        });
    }

    private void updateLabels(Ipapi ipInfo) {

        // Calling code -------------------

        String callingCode = ipInfo.getLocation().getCallingCode();
        String formattedCallingCode = "+" + callingCode;

        callingcodeText.setText(formattedCallingCode);

        // City, state -------------------

        String cityTextValue = (ipInfo.getCity() != null)
                ? String.format("%s %s", ipInfo.getCity(), "(" + ipInfo.getRegionName() + ")")
                : "Información de ciudad no disponible";

        cityText.setText(cityTextValue);

        // Latitude, longitude and zip -------------------

        latitudeText.setText(ipInfo.getLatitude().toString());
        longitudeText.setText(ipInfo.getLongitude().toString());
        zipText.setText(ipInfo.getZip());

        // Language code and language -------------------

        Language language = ipInfo.getLocation().getLanguages().get(0);
        String languageTextValue = (language != null)
                ? String.format("%s (%s)", language.getName(), language.getCode().toUpperCase())
                : "Información de idioma no disponible";

        languageText.setText(languageTextValue);

        // Flag image -------------------

        String countryCode = ipInfo.getCountryCode();
        String flagImagePath = "/flags/" + countryCode + ".png";

        InputStream stream = getClass().getResourceAsStream(flagImagePath);
        if (stream != null) {
            Image countryFlagImage = new Image(stream);
            flagImage.setImage(countryFlagImage);
        } else {
            System.out.println("No se pudo cargar la imagen del código de pais: " + countryCode);
        }

        // IP Location country and country code -------------------

        String iplocationTextValue = ipInfo.getCountryName() + " (" + ipInfo.getCountryCode() + ")";
        iplocationText.setText(iplocationTextValue);

        // TODO: Currency y TimeZone son valores nulos, hay que investigarlos

        // Timezone -------------------

        TimeZone timeZone = ipInfo.getTimeZone();
        String timezoneTextValue = (timeZone != null)
                ? timeZone.getCode()
                : "N/A";

        timezoneText.setText(timezoneTextValue);

        // Currency -------------------

        Currency currency = ipInfo.getCurrency();
        String currencyTextValue = (currency != null)
                ? String.format("%s (%s)", currency.getName(), currency.getSymbol())
                : "N/A";

        currencyText.setText(currencyTextValue);

    }

    // Método para realizar la llamada a la API GeoFX con la IP obtenida
    private void getIpInfo(String ipAddress) {
        Call<Ipapi> call = geoFXService.getIpInfo(ipAddress);
        call.enqueue(new Callback<Ipapi>() {
            @Override
            public void onResponse(Call<Ipapi> call, Response<Ipapi> response) {
                if (response.isSuccessful()) {
                    Ipapi ipInfo = response.body();
                    Platform.runLater(() -> updateLabels(ipInfo));
                } else {
                    System.out.println("Error al obtener información de IP. Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Ipapi> call, Throwable t) {
                System.out.println("Error de red al obtener información de IP: " + t.getMessage());
            }
        });
    }

}
