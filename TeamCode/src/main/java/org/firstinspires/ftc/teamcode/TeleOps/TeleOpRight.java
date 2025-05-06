package org.firstinspires.ftc.teamcode.TeleOps;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;

@TeleOp(name = "Teleop RIGHT!!")
public class TeleOpRight extends NextFTCOpMode {
    public TeleOpRight(){
        super(Claw.INSTANCE, /*Lift.INSTANCE,*/ Arm.INSTANCE);
    }

    // Chassis
    public String frontLeftName = "leftFront";
    public String frontRightName = "rightFront";
    public String backLeftName = "leftBack";
    public String backRightName = "rightBack";

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

        motors = new MotorEx[]{frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor};
    }
    @Override
    public void onStartButtonPressed(){
        IMU imu = hardwareMap.get(IMU.class, "imu");
        boolean isClawOpen = false;
        driverControlled = new MecanumDriverControlled(motors, gamepadManager.getGamepad1(), false, imu);
        driverControlled.invoke();

        // Lift setters
        /*gamepadManager.getGamepad2().getDpadUp().setPressedCommand(Lift.INSTANCE::toHigh);
        gamepadManager.getGamepad2().getDpadLeft().setPressedCommand(Lift.INSTANCE::toMiddle);
        gamepadManager.getGamepad2().getDpadDown().setPressedCommand(Lift.INSTANCE::toLow);*/

        //Claw
        if (isClawOpen){
            gamepadManager.getGamepad2().getCross().setPressedCommand(Claw.INSTANCE::close);
            isClawOpen = false;
        } else {
            gamepadManager.getGamepad2().getCross().setPressedCommand(Claw.INSTANCE::open);
            isClawOpen = true;
        }
        //gamepadManager.getGamepad2().getCross().setPressedCommand(Claw.INSTANCE::toggle);
    }
}

