// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.ElevatorFeedforward;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Screw;

public class TicTacToe extends CommandBase {
  /** Creates a new TicTacToe. */
  ElevatorFeedforward elevator = new ElevatorFeedforward(Constants.charConsts.ks, Constants.charConsts.kg, Constants.charConsts.kv);
  PIDController pid = new PIDController(Constants.pidConsts.pidP, Constants.pidConsts.pidI, Constants.pidConsts.pidD);
  private Screw screw;
  private double pos;

  public TicTacToe(Screw sc, double pos) {
    screw = sc;
    this.pos = pos;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pid.setSetpoint(pos);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    screw.spin(pid.calculate(screw.getPosition()) + elevator.calculate(0));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
