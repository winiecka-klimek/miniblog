package javaldz26.demo.miniblog26.hello;

import org.springframework.stereotype.Service;

@Service
public class ConstantTemperatureService implements TemperatureProvider {

    @Override
    public Integer currentTemperature() {
        return 23;
    }
}
