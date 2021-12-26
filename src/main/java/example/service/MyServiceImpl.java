package example.service;

import org.jvnet.hk2.annotations.Service;

@Service
public class MyServiceImpl implements MyService {
    @Override
    public String get() {
            return "Hello";
    }
}
