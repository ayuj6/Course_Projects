
public class MyLevels {

	
	public static final char[][][] LEVELS = {
		{
			//{' ', ' ', ' '},
            //{' ', '=', ' '},
            //{'@', ' ', ' '},
			{Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
			{Config.EMPTY_CHAR,Config.BOX_CHAR,Config.EMPTY_CHAR},
			{Config.WORKER_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR}
		},
		{
			//{' ', ' ', ' ', '#', ' ', ' '},
            //{' ', '#', '=', '#', '#', '#'},
            //{'#', '#', ' ', '#', ' ', ' '},
			//{' ', ' ', ' ', ' ', ' ', ' '},
            //{' ', ' ', ' ', ' ', '=', ' '},
            //{'@', ' ', ' ', '#', '#', ' '},
			{Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
			{Config.EMPTY_CHAR,Config.WALL_CHAR,Config.BOX_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR},
			{Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
			{Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
			{Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.BOX_CHAR,Config.EMPTY_CHAR},
			{Config.WORKER_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR},
		},
		{
			//{' ', '#', '#', '#', '#', '#', '#', '#', ' '},
			//{' ', '=', ' ', '#', ' ', '#', ' ', ' ', ' '},
			//{' ', ' ', ' ', '#', ' ', '#', ' ', '=', ' '},
			//{'#', '#', ' ', '#', '#', '#', ' ', ' ', '#'},
			//{'#', '#', ' ', '#', '#', '#', ' ', ' ', '#'},
			//{' ', ' ', ' ', ' ', '#', '#', '#', ' ', '#', '#', '#'},
			//{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			//{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '=', '#'},
			//{'@', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' '},
			{Config.EMPTY_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR},
			{Config.EMPTY_CHAR,Config.BOX_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
			{Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.BOX_CHAR,Config.EMPTY_CHAR},
			{Config.WALL_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR},
			{Config.WALL_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR},
			{Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR},
			{Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.WALL_CHAR},
			{Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.BOX_CHAR,Config.WALL_CHAR},
			{Config.WORKER_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.WALL_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.EMPTY_CHAR},
		}
};
	
	
    public static final int[][] GOALS = { {1,2},{0,2,5,5},{0,8,0,0,8,10} };
}