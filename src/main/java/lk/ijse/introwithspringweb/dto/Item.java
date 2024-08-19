package lk.ijse.introwithspringweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    private List<String> [] id;
    private List<String>[] desc;
}
