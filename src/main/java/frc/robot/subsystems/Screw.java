// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Screw extends SubsystemBase {
  /** Creates a new Screw. */
  private final WPI_TalonSRX _screwTalon;
  public static double _nutPos;

  public Screw() {
    _screwTalon = new WPI_TalonSRX(Constants.ScrewConsts.screwPort);

    //_screwTalon.configFactoryDefault();
    _screwTalon.setInverted(false);
    //_screwTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

    _nutPos = Constants.ScrewConsts.startPos;
  }


  public void spin(double speed){
    _screwTalon.set(ControlMode.PercentOutput, speed);
  }
  
  public double getPosition(){ 
    return -1 * _screwTalon.getSelectedSensorPosition() * Constants.threadLength * Constants.gearBoxRatio / 4096.0;
  }
  public void setPosition(double pos){
    _screwTalon.setSelectedSensorPosition(pos);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Position", getPosition());
    // This method will be called once per scheduler run
  }
}
