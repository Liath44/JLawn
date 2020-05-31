import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Exporter 
	{
	public void exportSprinklerList(Lawn lawn, String path) throws IOException
		{
		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		writer.write("Number of sprinklers: ");
		writer.write(lawn.getSprinklerNumber());
		writer.write("\n\n");
		for(Sprinkler sprinkler: lawn.getSprinklers())
			{
			writer.write("type: ");
			writer.write(sprinkler.getType());
			writer.write(" x = ");
			writer.write(sprinkler.getx());
			writer.write(" y = ");
			writer.write(sprinkler.gety());
			writer.write(" degree = ");
			writer.write(sprinkler.getDeg());
			writer.write("\n");
			}
		}
		
	
	}
