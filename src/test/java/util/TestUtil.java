package util;

import dto.Category;
import dto.Pet;
import dto.Tag;

import java.util.Collections;

public class TestUtil {

    public static Pet intializePetObj() {
        Category c = new Category();
        c.setId(0);
        c.setName("string");

        Tag t = new Tag();
        t.setId(0);
        t.setName("string");

        Pet pet = new Pet();
        pet.setId(26);
        pet.setCategory(c);
        pet.setName("duck1");
        pet.setPhotoUrls(Collections.singletonList("Str"));
        pet.setTags(Collections.singletonList(t));
        pet.setStatus("available");
        return pet;
    }

}
