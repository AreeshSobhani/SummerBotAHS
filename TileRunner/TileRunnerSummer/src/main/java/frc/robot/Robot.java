package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drivetrain;



public class Robot extends TimedRobot {


  Command autoCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  public static Controls controls = new Controls();
  public static Drivetrain drivetrain = new Drivetrain();
  public static Cameras cameras = new Cameras();

  //public static Drivetrain drive;

  @Override
  public void robotInit() {
    

    m_chooser = new SendableChooser<Command>();
    
    m_chooser.setDefaultOption("Default Auto", new DriveCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

    //controls = new Controls();
    ///drivetrain = new Drivetrain();
  }


  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
    Scheduler.getInstance().removeAll();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
 
  }

 
  @Override
  public void autonomousInit() {
  
    
    autoCommand = m_chooser.getSelected();
		autoCommand = (Command) m_chooser.getSelected();
		
		if (autoCommand != null) {
			autoCommand.start();
		}
  }


  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  

    if (autoCommand != null) {
      autoCommand.cancel();
    }

    new DriveCommand().start();
  }

  
  @Override
  public void teleopPeriodic() {  
    Scheduler.getInstance().run();
  }

 
  
  @Override
  public void testPeriodic() {
  }
}