package visma;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import javaslang.control.Option;
import visma.Dto.Item;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class FileParser {

    private static final String SAMPLE_CSV_FILE_PATH = "sample.csv";

    List<Item> csvReader() {
        List<Item> list = new ArrayList<>();
        javaslang.collection.List<Item> test = javaslang.collection.List.empty();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        ) {
            CsvToBean<Item> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Item.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (Item item : (Iterable<Item>) csvToBean) {

                Option<Item> test2 = test.find(it ->
                        item.getId().equals(it.getId()) && item.getName().equals(it.getName()) && item.getDate().equals(it.getDate())
                );
                if (test2.isDefined()) {
                    test.forEach(it -> {
                        if (item.getId().equals(it.getId()) &&
                                item.getName().equals(it.getName()) &&
                                item.getDate().equals(it.getDate())) {

                            it.setQty(it.getQty() + item.getQty());

                        }
                    });
                } else {
                    test = test.append(item);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return sort(test.toJavaList());
    }

    static List<Item> sort(List<Item> list) {
        return list.stream()
                .sorted(Comparator.comparing(Item::getName))
                .collect(Collectors.toList());
    }


}
