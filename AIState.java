
public enum AIState 
{
	RUNNING
	{
		public AIState here()
		{
			if (Board.win && !Board.dead)
				return WIN;
			if (!Board.win && Board.dead)
				return LOSE;
			else
				return RUNNING;
		}
	},
	WIN
	{
		public AIState here()
		{
			System.out.println("YOU WIN. CONGRATS");
			return EXIT;
		}
	},
	LOSE
	{
		public AIState here()
		{
			System.out.println("SUCKS TO BE YOU. YOU DED.");
			return EXIT;
		}
	},
	EXIT
	{
		public AIState here()
		{
			return null;
		}
	};
	
	public abstract AIState here();
}
