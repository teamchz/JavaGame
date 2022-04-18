package com.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class OBJ_ET extends SuperObject {
    public OBJ_ET() {
        name = "ET";

        Random random = new Random();
        if(imageCounter == 0) {
            energy = 50 + random.nextInt(9) * 5;
            imageCounter = 1;
        }

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/ET/ET-"+energy+".png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}

