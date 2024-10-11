package AndrewWebServices;

public class FakeDatabase extends Database {
    @Override
    public int getPassword(String accountName) {
        if (accountName == "Scotty") {
            return 17214;
        } else {
            return 0;
        }
    }
}
