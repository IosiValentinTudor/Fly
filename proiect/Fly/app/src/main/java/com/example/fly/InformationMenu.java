package com.example.fly;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformationMenu extends AppCompatActivity {

    TextView titlu1,titlu2,titlu3,titlu4;
    TextView text1,text2,text3,text4;
    Button beginer,advanced,safety,gear;
    Drawable draw1,draw2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_menu);


        titlu1 = findViewById(R.id.titlu1);
        titlu2 = findViewById(R.id.titlu2);
        titlu3 = findViewById(R.id.titlu3);
        titlu4 = findViewById(R.id.titlu4);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);

        beginer = findViewById(R.id.beginer);
        advanced = findViewById(R.id.advanced);
        safety = findViewById(R.id.safety);
        gear = findViewById(R.id.gear);

        draw1 = getResources().getDrawable(R.drawable.custom_button2);
        draw2 = getResources().getDrawable(R.drawable.custom_unselected_button2);


        beginer.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                beginer.setBackground(draw1);
                advanced.setBackground(draw2);
                safety.setBackground(draw2);
                gear.setBackground(draw2);

                titlu1.setText("1.  Book a tandem flight to know how paragliding is");
                text2.setText("     Once you are up in the air the paragliding experience may feel very different from what you expected. This is the reason why our first paragliding tip is that you book a tandem paragliding flight. That will give you the chance to fly with a professional who will show you how it feels to fly like a bird, but you won’t have the added stress of controlling the situation and the huge paraglider wing floating above your head.");

                titlu2.setText("2.  Sign up for a paragliding course");
                text2.setText("     Once you have tried the paragliding experience and you have decided that you absolutely love it, it is probably that you want to take it further and you have decided that the next step is to become a paragliding pilot. Thus, if you have always dreamt with becoming a pilot, you just have to sign up for a paragliding course with certified professionals.\n" +
                        "\n" +
                        "Once you have received your paragliding lessons, you will receive your paragliding license. Then, you will have the chance to buy your own equipment if you haven’t received it in the Paragliding School. Although it may seem a bit expensive at the beginning, remember that achieving your dreams is priceless!");

                titlu3.setText("3.  Join a good paragliding club");
                text3.setText("Once you have made the Club Pilot course, you have the chance to fly within a club environment, so you can join a paragliding club. We strongly recommend you trying to find a club close to your home, or otherwise, the club your Paragliding School is involved in. Once you join a paragliding club, you have the chance to interact with other pilots who will share their experiences and will give you advice about paragliding.");


                titlu4.setText("4.  Don’t fly on your own — make friends and fly with them!");
                text4.setText("     Although this paragliding tip might sound a bit silly, it is clear that the best option to make paragliding friends is to join a paragliding club. Surely, most of them will have many years of experience from which you can learn. That will definitely help you to progress and improve your skills.\n" +
                        "\n" +
                        "When you start your paragliding life, it can be difficult to make the right decision about which is the best paragliding site to fly depending on several factors like the weather conditions and so on. Nevertheless, if you have paragliding friends, you won’t have to worry about it, because they will help you. ");


            }
        });

        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {

                beginer.setBackground(draw2);
                advanced.setBackground(draw1);
                safety.setBackground(draw2);
                gear.setBackground(draw2);


                titlu1.setText("1.  Get Fast Slowly");
                text1.setText("     It may sound like a contradiction, but probably the best way to reach the top speed is to engage your speed bar progressively. This is one of the best paragliding tips for faster flying.\n" +
                        "\n" +
                        "While beginners may be tempted to stamp on the bar right away, this approach can pitch the glider forward with too steep an angle of attack, making the wing vulnerable to deflations. Instead, ease on the speed by monitoring your pitch and waiting until the speed of your body matches the wing speed before increasing it.");

                titlu2.setText("2.  Soften Your Steering");
                text2.setText("     This paragliding tip in simple words — sharp steering feeds your sink rate. You have to try manoeuvring your wing more uniformly to avoid tight turns and abrupt shifts which can also oscillate your banking angle. You just have to bank the glider in strong lift.");

                titlu3.setText("3.  Ride the Thermal");
                text3.setText("     If you know how to centre a thermal, it can mean the difference between getting home safe and sound, and landing out. Thermals don’t just offer a chance to gain altitude, but they can also send you flying at speeds on a par with the speed of birds.\n" +
                        "\n"+
                        "If you want to make the most of a thermal, you have to wait until you feel the strongest lift and then dig your wing into it with a tight turn. The faster you find the core of the thermal, the faster you will ascend and the faster you will fly. This is another of the best paragliding tips for faster flying.");


                titlu4.setText("4.  Try Ridge Soaring");
                text4.setText("     If you plan your route over ridges, it can help supply a steady stream of updrafts. In good wind conditions, slopes can provide a fairly rational lift as long as you fly steadily over the edges. This paragliding technique is often used in competition tasks to run down long ridges. You just have to ascend to the top of the thermal if you intend to leave the ridge. In other words, go over the back or into the flats.");



            }
        });

        safety.setOnClickListener(new View.OnClickListener() {
            @Override@SuppressLint("SetTextI18n")

            public void onClick(View v) {

                beginer.setBackground(draw2);
                advanced.setBackground(draw2);
                safety.setBackground(draw1);
                gear.setBackground(draw2);

                titlu1.setText("");
                text1.setText("     As you probably know, paragliding is an exciting sport that everybody can enjoy! Although it is extremely sure, even more than driving a car, it could involve some risks due to the fact that it involves flying through the air. This means hundreds or thousands of meters above the ground, and that is the reason why taking some steps to ensure your safety is a must.\n" +
                        "\n" +
                        "Nevertheless, one of the most dangerous aspects of paragliding is the period just after someone starts to feel comfortable in the air.\n" +
                        "\n" +
                        "On the one hand, when you are learning to paraglide, it is quite probable that you will be extremely careful and that you even have a feeling of vulnerability. On the other hand, if you have already gone through the paragliding training to become a pilot and you have already gone out to fly by yourself, it can be pretty easy to develop a false sense of security.\n" +
                        "\n" +
                        "It is very easy to take safety less seriously and make mistakes at the moment when you start to feel confident. Down below, you will find some paragliding safety tips geared towards everyone — from beginners to the most experienced paragliding pilots.");


                titlu2.setText("Paragliding Preparation");
                text2.setText("Paragliding preparation is the key. You must bear in mind that errors occur, even to experienced pilots. However, you have to maintain respect for flying, together with a constant feeling of susceptibility. Moreover, you must prepare yourself properly both mentally and physically before your paragliding flight, because it is very important.\n" +
                        "\n" +
                        "This paragliding safety tip also includes collecting an accurate weather forecast before your paragliding flight, as well as knowing the area very well.\n" +
                        "\n" +
                        "You must also remember to check all paragliding equipment regularly and before every flight to make sure that it is up to proper working standards. If you have any doubt or question about the integrity of any piece of your paragliding equipment, or about the conditions in which you will be doing your flight, you better don’t take off.\n" +
                        "\n" +
                        "We strongly recommend you to practice your paragliding techniques for both taking-off and landing too. When you are landing, you have to do so into the wind. It is important to practice your landing technique. We recommend you to concentrate your efforts on hitting the target, or at least, land as close as possible.\n" +
                        "\n" +
                        "Furthermore, you must have a plan for the end of your flight before you have even begun. It must be a priority at every step of your flight to plan properly a safe approach to the landing zone. As we have said, you should avoid landing out of the landing zone, because if you do that, it will increase the probability of suffering a paragliding accident.\n" +
                        "\n" +
                        "Practice makes perfection, and every effort has its reward so, believe us when we say that, once you reach the ground safely, you will be happy to have gone through all of those details before flying.");


                titlu3.setText("");
                text3.setText("");

                titlu4.setText("");
                text4.setText("");

            }
        });

        gear.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {

                beginer.setBackground(draw2);
                advanced.setBackground(draw2);
                safety.setBackground(draw2);
                gear.setBackground(draw1);

                titlu1.setText("");
                text1.setText("Choosing gear to paraglide for the first time might feel like an overwhelming, daunting task. As you browse through respected paragliding gear sites, you will come across tons of different options from varying brands to choose from, which can easily make any newbie’s head spin. What might make this even more confusing is the reality that each piece of gear for paragliding you come across is not necessarily better than the other, but more so targeted to fulfill certain pilot needs." +
                        "\n");

                titlu2.setText("");
                text2.setText("There are quite a few things to keep in mind when picking out the right gear for paragliding. Starting with the wing itself, understanding the different classes is key. You have first wings (great for first-timers), progression wings for those who are ready to upgrade, and then you get into the more experienced ones like XC class, performance class, advanced class, and competition class. For just starting out, you will either want to go with first wings or progression." +
                        "\n"+
                        "Try going with a standard certified glider. EN-A ones are the easiest to control, whereas EN-D and CCC ones are more difficult and should be handled by pros.");

                titlu3.setText("");
                text3.setText("Keep the weight of your gear in the back of your mind. With all of your equipment, it can easily add 30-40 pounds on top of what you already weigh. And because paragliders tend to reach peak performance when you are at about 50 to 75% of the weight range, make sure to look into what the actual percentage is when purchasing a wing so you can work around staying in the ideal weight window when buying the rest of the gear.\n" +
                        "For your harness, this acts as more than just a chair for you; it can be your buffer to protect you if you happen to fall. Many modern harnesses have airbags as well for extra protection. Along with this, some are reclined, some are shaped like a pod, and some are entirely upright. As for your advice here, go with the standard upright harness if you are a first-time buyer. And though there is not a classification system for the majority of harnesses, make sure to get one that is both EN 1651 (load tested) and LTF91/09 (protection tested).\n" +
                        "The last big one is your helmet. Most pilots prefer to invest in a helmet that contains a faceguard as a way to protect themselves from both crashing risks and the wind. Nonetheless, this one is up to you. For some reference, helmets without a shield offer more “freedom” during flight, but the shielded ones are generally safer.");

                titlu4.setText("");
                text4.setText("");




            }
        });

    }
}