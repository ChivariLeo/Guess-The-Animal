package leo.game;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


@Getter
@Component
public class AnimalRepositoryImpl implements AnimalRepository {

    private  final Map<String,String> animals=new HashMap<>();


    @Autowired
    public AnimalRepositoryImpl() throws FileNotFoundException {
        File file = new File("C:\\Users\\admin\\Desktop\\Java\\Guess The Animal\\core\\src\\main\\resources\\game_content.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String[] content = scanner.nextLine().split("::=");
            String name = content[0];
            String description = content[1];
            animals.put(name, description);
        }
    }


    @Override
    public String[] animalNames() {
        return animals.keySet().toArray(new String[0]);
    }

    @Override
    public String[] animalDescriptions() {
        return animals.values().toArray(new String[0]);
    }


}
