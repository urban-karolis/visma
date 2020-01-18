package visma;

import visma.Dto.Item;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


class FilterUtils {


    static List<Item> filterBBE(List<Item> list, String date) {
        return list.stream().filter(item ->
                item.getDate().compareTo(Date.valueOf(date)) >= 0)
                .collect(Collectors.toList());

    }


    static List<Item> filterQty(List<Item> list, int qty) {
        return list.stream().filter(item ->
                item.getQty() < qty)
                .collect(Collectors.toList());

    }
}
