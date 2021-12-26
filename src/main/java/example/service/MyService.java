package example.service;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface MyService {
    static String SERVICE_STRING = "SERVICE_STRING";
    String get();
}
