package example.com.materialdesigndemo;

import java.util.concurrent.Future;

/**
 * Created by rhm on 2017/4/1.
 */

public class Fruit {
    private  int imageId;
    private String fruitName;


    public Fruit(int imageId, String fruitName) {
        this.imageId = imageId;
        this.fruitName = fruitName;
    }



    public int getImageId() {
        return imageId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
