package u2.oop.homework;

public class MainApp {
    public static void main(String[] args) {
        // Reminder: ref type != object type
        Participant[] participants = new Participant[]{
                new Human(10, 1000),
                new Robot(2000, 2000),
        };

        Obstacle[] obstacles = new Obstacle[]{
                new Tunnel(10),
                new Road(1000),
                new Tunnel(2000),
                new Tunnel(10),
        };

        int i = 0;
        for (Obstacle obstacle : obstacles) {
            System.out.println(i++ + " : ");
            for (Participant participant : participants) {
                obstacle.action(participant);
            }
        }

    }
}


/*
ParticipantObstacle
	Obstacle abstract class
		void action (Participant p)
			Wall
				void action ( Participant p )
					p.jump ( dimensions )
			Road
				void action ( Participant p )
					p.run ( dimensions )
	Particpant interface
		void run ( int length )
			Human
				void run ( int length )
			Robot
				void run ( int length )
		void jump( int length )
			Human
				void jump ( int length )
			Robot
				void jump ( int length )


Another style:

ParticipantObstacle
	Obstacle abstract class
		void action (Participant p)
			Wall
				void action ( Participant p )
					p.jump ( this )
			Road
				void action ( Participant p )
					p.run ( this )
	Particpant interface
		void run ( Obstacle o )
			Human
				void run ( Obstacle o )
			Robot
				void run ( Obstacle o )
		void jump( Obstacle o )
			Human
				void jump ( Obstacle o )
			Robot
				void jump ( Obstacle o )
		void action ( Obstacle o )
			Human
				void action ( Obstacle o )
					o.action( this )
			Robot
				void action ( Obstacle o )
					o.action( this )

 */