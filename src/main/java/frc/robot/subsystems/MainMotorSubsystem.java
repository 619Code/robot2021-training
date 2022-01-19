// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANError;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MainMotorSubsystem extends SubsystemBase {

  private CANSparkMax mainMotor;
  private final int MainMotorIndex = 10;
  private DigitalInput cutOff;

  /** Creates a new ExampleSubsystem. */
  public MainMotorSubsystem() {

    // Initializes a DigitalInput on DIO
    cutOff = new DigitalInput(0);

    mainMotor = new CANSparkMax(MainMotorIndex, MotorType.kBrushless);
    mainMotor.restoreFactoryDefaults();

    if(mainMotor.setIdleMode(IdleMode.kCoast) != CANError.kOk){
      SmartDashboard.putString("Idle Mode", "Error");
    }
  }

  public void SetSpeed(double speed) {

    var cutOffState = !this.cutOff.get();
    if (cutOffState)
    {
      this.mainMotor.set(0);
      return;
    }

    if (speed < -1 || speed > 1)
      SmartDashboard.putString("Speed must be between -1 and 1", "Error");
    
    this.mainMotor.set(speed);
    // periodically read voltage, temperature, and applied output and publish to SmartDashboard
    SmartDashboard.putNumber("Voltage", mainMotor.getBusVoltage());
    SmartDashboard.putNumber("Temperature", mainMotor.getMotorTemperature());
    SmartDashboard.putNumber("Output", mainMotor.getAppliedOutput());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void logText(String logText) {
    System.out.println(logText);    
  }
}
