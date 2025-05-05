package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Claw extends Subsystem {
    // BOILERPLATE
    public static final Claw INSTANCE = new Claw();
    private Claw() { }

    // USER CODE
    public Servo servo;
    private boolean isOpen = false;
    
    public String name = "claw_servo";

    public Command open() {
        return new ServoToPosition(servo,
                0.9,
                this);
    }

    public Command close() {
        return new ServoToPosition(servo,
                0.2,
                this);
    }

    public Command toggle(){
        if(isOpen) {
            return close();
        } else {
            return open();
        }
    }

    @Override
    public void initialize() {
        servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
    }
}
