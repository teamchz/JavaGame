# JavaGame Multiplayer

### Member
> 63090500426 นิติพัฒน์ ศรีธระชิยานนท์

> 63090500429 พิษณุ บุญญาอนันต์
  
> 63090500439 พรพล ตั้งอดุลย์รัตน์



### explain package in project
-   `company` เป็น package หลัก ใช้สำหรับรวม class หลักที่ใช้ในการควบคุมเกม เช่น ควบคุมเกม, ควบคุม Input, รวมไปถึงการเชื่อมต่อ Client/Server

-   `entity` เป็น package สำหรับตัวละครผู้เล่น

-   `object` เป็น package สำหรับต่างๆในเกม เช่น ระเบิด, EnergyTank, กระสุน

-   `tile` เป็น package สำหรับการควบคุมแผนที่

-   `res` เป็น package สำหรับรวม image file ในการใช้ภายในโปรแกรม 



### how to compile and run.
1. เข้าไปที่ `folder JavaGame` เมื่อเปิด project มาให้เข้าไปที่ `folder src/com/company`
2. เลือก Run file `Main.java` เพื่อเริ่มการทำงาน
```java
public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JavaGame");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
        gamePanel.startGameServer();
    }
}
```


3. เมื่อรันไฟล์เสร็จสิ้นจะเกิดหน้าต่างสำหรับเล่นเกมของ player1 (Host) ขึ้นมา
  ![alt text](https://media.discordapp.net/attachments/720005228447137904/965501012787998790/unknown.png?width=792&height=654)


4. หากต้องการ player2 เข้าไปที่ `folder JavaGameMP` เมื่อเปิด project มาให้เข้าไปที่ `folder src/com/company`


5. เลือก Run file `MainMP.java` เพื่อเริ่มการทำงาน

```java
public class MainMP {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JavaGame");

        GamePanelMP gamePanelMP = new GamePanelMP();
        window.add(gamePanelMP);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanelMP.setupGame();
        gamePanelMP.client.StartConnect();
        gamePanelMP.startGameThread();
    }
}
```

6. เมื่อรันไฟล์เสร็จสิ้นจะเกิดหน้าต่างสำหรับเล่นเกมของ player2 (Client) ขึ้นมา
  ![alt text](https://media.discordapp.net/attachments/720005228447137904/965505762187178014/unknown.png?width=792&height=654)
  
7. สามารถเล่นด้วยกันได้
  ![alt text](https://media.discordapp.net/attachments/720005228447137904/965506252681658378/unknown.png?width=1270&height=654)
