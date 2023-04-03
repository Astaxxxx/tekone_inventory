import com.example.demo.module.Admin;
import com.example.demo.module.AdminCollection;
import org.junit.jupiter.api.Test;

public class TestAdmin {
    private AdminCollection ac;

    private Admin ad;


    @Test
    public void loadAdmin(){
        ac =new AdminCollection();
        ac.getAllAdmins();
    }
}
