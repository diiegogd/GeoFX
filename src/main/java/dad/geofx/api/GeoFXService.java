//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package dad.geofx.api;

import dad.geofx.api.model.Ipapi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoFXService {
    @GET("ip_api.php")
    Call<Ipapi> getIpInfo(@Query("ip") String ipAddress);
}
