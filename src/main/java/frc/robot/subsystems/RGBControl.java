// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// This is a subsystem that controls the RGB LED on the RoboRIO
// it works by creating a binary number from the three digital outputs,
// the arduino decodes this number and sets the LED accordingly
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Subsystem for the RGBPanel */
public class RGBControl extends SubsystemBase {

  private static final int redChannel = 0;
  private static final int greenChannel = 1;
  private static final int blueChannel = 2;
  private static final int commonChannel = 3;

  private static DigitalOutput select1 = new DigitalOutput(0);
  private static DigitalOutput select2 = new DigitalOutput(1);
  private static DigitalOutput select3 = new DigitalOutput(2);
  private static PowerDistribution m_pdp = new PowerDistribution(20, ModuleType.kRev);
  private static Solenoid red = new Solenoid(0, PneumaticsModuleType.CTREPCM, redChannel);
  private static Solenoid green = new Solenoid(0, PneumaticsModuleType.CTREPCM, greenChannel);
  private static Solenoid blue = new Solenoid(0, PneumaticsModuleType.CTREPCM, blueChannel);
  private static Solenoid common = new Solenoid(0, PneumaticsModuleType.CTREPCM, commonChannel);

  /** Creates a new RGBControl. */
  public RGBControl() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    m_pdp.setSwitchableChannel(DriverStation.isEnabled());
  }

  public Command Command3360() {
    return this.runOnce(
        () -> {
          select1.set(true);
          select2.set(false);
          select3.set(false);
        });
  }

  public Command blueCommand() {
    return this.runOnce(
        () -> {
          select1.set(false);
          select2.set(true);
          select3.set(true);
        });
  }

  public Command greenCommand() {
    return this.runOnce(
        () -> {
          select1.set(true);
          select2.set(false);
          select3.set(true);
        });
  }

  public Command redCommand() {
    return this.runOnce(
        () -> {
          select1.set(false);
          select2.set(false);
          select3.set(true);
        });
  }

  public Command yellowCommand() {
    return this.runOnce(
        () -> {
          select1.set(false);
          select2.set(true);
          select3.set(false);
        });
  }

  public Command purpleCommand() {
    return this.runOnce(
        () -> {
          select1.set(true);
          select2.set(true);
          select3.set(false);
        });
  }

  public Command teamCommand() {
    return this.runOnce(
        () -> {
          select1.set(false);
          select2.set(false);
          select3.set(false);
        });
  }

  public void off() {
    red.set(true);
    green.set(true);
    blue.set(true);
    common.set(true);
  }

  public static void red() {
    red.set(false);
    green.set(true);
    blue.set(true);
    common.set(true);
  }

  public static void green() {
    red.set(true);
    green.set(false);
    blue.set(true);
    common.set(true);
  }

  public void blue() {
    red.set(true);
    green.set(true);
    blue.set(false);
    common.set(true);
  }
}
