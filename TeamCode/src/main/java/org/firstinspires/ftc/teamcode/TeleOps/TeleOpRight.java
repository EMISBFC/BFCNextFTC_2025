package org.firstinspires.ftc.teamcode.TeleOps;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;

@Config
@TeleOp(name = "Teleop RIGHT!!")
class TeleOpRight extends NextFTCOpMode {
    public TeleOpRight(){
        super(Claw.INSTANCE, Lift.INSTANCE);
    }

    // Chassis
    public String frontLeftName = "fl";
    public String frontRightName = "fr";
    public String backLeftName = "bl";
    public String backRightName = "br";

    public MotorEx frontLeftMotor;
    public MotorEx frontRightMotor;
    public MotorEx backLeftMotor;
    public MotorEx backRightMotor;

    public MotorEx[] motors;

    public Command driverControlled;

    @Override
    public void onInit() {
        frontLeftMotor = new MotorEx(frontLeftName);
        frontRightMotor = new MotorEx(frontRightName);
        backLeftMotor = new MotorEx(backLeftName);
        backRightMotor = new MotorEx(backRightName);

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        motors = new MotorEx[]{frontRightMotor, frontLeftMotor, backRightMotor, backLeftMotor};
    }
    @Override
    public void onStartButtonPressed(){
        driverControlled = new MecanumDriverControlled(motors, gamepadManager.getGamepad1());
        driverControlled.invoke();

        // Lift setters
        gamepadManager.getGamepad2().getDpadUp().setPressedCommand(Lift.INSTANCE::toHigh);
        gamepadManager.getGamepad2().getDpadLeft().setPressedCommand(Lift.INSTANCE::toMiddle);
        gamepadManager.getGamepad2().getDpadDown().setPressedCommand(Lift.INSTANCE::toLow);

        //Claw
        gamepadManager.getGamepad2().getCross().setPressedCommand(Claw.INSTANCE::toggle);
    }
}

