package org.firstinspires.ftc.teamcode.Subsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;

public class Arm extends Subsystem{
    public static final Arm INSTANCE = new Arm();
    private Arm() { };

    public MotorEx motor;
    public PIDFController controller = new PIDFController(0.0,0.0,0.0);
    public Command resetZero() {
        return new InstantCommand(() -> { motor.resetEncoder(); });
    }
    public String name = "arm";

    public Command armToHang(){
        return new RunToPosition(motor,
                70,
                controller,
                this);
    }

    public Command armToPut(){
        return new RunToPosition(motor,
                250,
                controller,
                this);
    }

    public Command armToTransition(){
        return new RunToPosition(motor,
                -28,
                controller,
                this);
    }

    public Command armToGrab(){
        return new RunToPosition(motor,
                340,
                controller,
                this);
    }

    @Override
    public void initialize() {
        motor = new MotorEx(name);
    }
}
