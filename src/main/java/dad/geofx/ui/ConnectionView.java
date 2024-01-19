package dad.geofx.ui;

import dad.geofx.api.GeoFXService;
import dad.geofx.api.model.Ipapi;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionView implements Initializable {

    @FXML
    private Label asnText;

    @FXML
    private TextField ipText;

    @FXML
    private Label ipaddressText;

    @FXML
    private Label hostnameText;

    @FXML
    private Label registeredispText;

    @FXML
    private Label typeText;

    @FXML
    private BorderPane view;

    private final GeoFXService geoFXService;

    public ConnectionView() {
        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ipapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear instancia de GeoFXService
        geoFXService = retrofit.create(GeoFXService.class);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConnectionView.fxml"));
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

        // IP Address -------------------

        ipaddressText.setText(ipInfo.getIp());

        // Registered ISP -------------------

        registeredispText.setText(ipInfo.getConnection().getIsp());

        // Type -------------------

        typeText.setText(ipInfo.getType());

        // ASN -------------------

        asnText.setText(ipInfo.getConnection().getAsn().toString());

        // Hostname -------------------

        hostnameText.setText(ipInfo.getHostname());

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
