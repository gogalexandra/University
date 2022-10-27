package dto.driver;

import entities.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class GetDriversDTO implements Serializable {
    List<Driver> drivers;
}
