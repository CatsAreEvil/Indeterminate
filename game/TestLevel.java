package game;

import java.awt.Color;
import java.awt.Shape;

import framework.GodRay;
import framework.Image;
import framework.KeyState;
import framework.Level;
import framework.LevelManager;
import framework.ShapeFactory;
import framework.ShapeUtils;
import framework.TextBox;
import framework.Wall;
import framework.WallTypes;
import framework.imageFilters.BasicVariance;
import framework.imageFilters.BlurEdges;
import framework.imageFilters.DarkenFrom;
import framework.imageFilters.ImageFilter;
import framework.imageFilters.LightFrom;
import framework.imageFilters.LightenFrom;
import framework.imageFilters.Outline;
import framework.imageFilters.ShadeDir;

public class TestLevel extends Level{
	private Player player;
	public TestLevel(LevelManager manager, GameLevels thisLevel) {
		super(manager, thisLevel.ordinal());

		player = new Player(this);
		this.addObject(player);
		
		GodRay ray = new GodRay(40, 0, 55, 0,
				500, 230, 500, 280, this, 30, new Color(240, 232, 14, 25));
		this.addObject(ray);
		
		Wall floor = new Wall(this, 10, 400, 400, 400, 10, WallTypes.FLOOR);
		floor.setBackground(Color.DARK_GRAY, false);
		floor.getBackground().addFilter(new BasicVariance(25));
		floor.getBackground().addFilter(new LightenFrom(ShadeDir.TOP, 3));
		this.addObject(floor);
		
		Wall rRamp = new Wall(this, 400, 400, 500, 300, 20, WallTypes.RAMP);
		rRamp.setBackground(Color.BLUE, false);
		this.addObject(rRamp);
		
		Wall lRamp = new Wall(this, 10, 350, 60, 400, 10, WallTypes.RAMP);
		lRamp.setBackground(Color.BLUE, false);
//		lRamp.getBackground().addFilter(new BasicVariance(20));
//		lRamp.getBackground().addFilter(new LightenFrom(ShadeDir.LEFT, 1));
		lRamp.getBackground().addFilter(new LightFrom(145, 0, 150, 0, 1, Color.WHITE));
		this.addObject(lRamp);
		
		Wall rWall = new Wall(this, 500, 100, 500, 300, 15, WallTypes.WALL);
		rWall.setBackground(Color.DARK_GRAY, false);
		rWall.getBackground().addFilter(new BasicVariance(25));
		rWall.getBackground().addFilter(new LightenFrom(ShadeDir.TOP, 0.4));
		this.addObject(rWall);
		Wall lWall = new Wall(this, 10, 100, 10, 350, 15, WallTypes.WALL);
		lWall.setBackground(Color.BLUE, true);
		this.addObject(lWall);
		
		
		Shape testIcon = ShapeFactory.solIconBar();
		testIcon = ShapeUtils.scale(testIcon, 3.3);
		Image icon = new Image(testIcon, Color.GREEN, this);
		testIcon = ShapeFactory.solIconBar();
		testIcon = ShapeUtils.scale(testIcon, 3.3);
		testIcon = ShapeUtils.flipX(testIcon);
		icon.addShape(testIcon, Color.RED, 60, 0);
		icon.addFilter(new DarkenFrom(ShadeDir.BOTTOM, 4));
		
//		Shape testIcon = ShapeFactory.squareCutCornors(4, 1);
//		testIcon = ShapeUtils.scale(testIcon, 6);
//		Image icon = new Image(testIcon, Color.RED, this);
//		icon.addFilter(new DarkenFrom(ShadeDir.TOP, 2));
//		icon.addFilter(new LightenFrom(ShadeDir.BOTTOM, 2));
		TextBox text = new TextBox("Hello World! I would like this to be two lines, so that it will fit in the area allowed,"
				+ " instead of overflowing, which is a bad thing.", 200, 410, 500, 60, icon, this);
		
		this.addUI(text);
	}
	@Override
	public void update(double dt, KeyState keys)
	{
		if (keys.esc_key)
		{
			this.getManager().setLevel(GameLevels.MENU.ordinal());
		}
		
		super.update(dt, keys);
	}
	

}
