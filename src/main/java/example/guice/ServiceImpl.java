package example.guice;

import com.google.inject.Singleton;

@Singleton
public class ServiceImpl implements Service {
    public String get() {
        return "service";
    }
}
