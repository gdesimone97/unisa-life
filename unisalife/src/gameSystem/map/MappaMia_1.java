package gameSystem.map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import game.GameObjects.Block;
import game.GameObjects.Player;
import game.GameObjects.Position;
import java.util.HashMap;



public class MappaMia extends ApplicationAdapter {
    private TiledMap map;
    private AssetManager manager;
    private int tileWidth, tileHeight,
	            mapWidthInTiles, mapHeightInTiles,
	            mapWidthInPixels, mapHeightInPixels;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    private final String mapFileName;

    private TextureAtlas playerAtlas;
    private Player player;
    private int[] background = new int[] {0}, foreground = new int[] {2};
    private HashMap<Position, Block> blocks;

    public MappaMia(String mapFileName) {
        this.mapFileName = mapFileName;
    }
    
    

     @Override
	public void create () {
		// Map loading
		manager = new AssetManager();
		manager.setLoader(TiledMap.class, new TmxMapLoader());
		manager.load(mapFileName, TiledMap.class);
		manager.finishLoading();
		map = manager.get(mapFileName, TiledMap.class);
		
		// Read properties
		MapProperties properties = map.getProperties();
		tileWidth         = properties.get("tilewidth", Integer.class);
		tileHeight        = properties.get("tileheight", Integer.class);
		mapWidthInTiles   = properties.get("width", Integer.class);
		mapHeightInTiles  = properties.get("height", Integer.class);
		mapWidthInPixels  = mapWidthInTiles  * tileWidth;
		mapHeightInPixels = mapHeightInTiles * tileHeight;
		
		// Set up the camera
		camera = new OrthographicCamera();
                camera.setToOrtho(false,mapWidthInPixels*.5f,mapHeightInPixels*.5f);
		camera.position.x = mapWidthInPixels * .5f;
		camera.position.y = mapHeightInPixels * .35f;
                camera.zoom = 1.2f;
		
                
                playerAtlas = new TextureAtlas("img/player/player.pack");
		Animation still, left, right;
		still = new Animation(1 / 2f, playerAtlas.findRegions("still"));
		left = new Animation(1 / 6f, playerAtlas.findRegions("left"));
		right = new Animation(1 / 6f, playerAtlas.findRegions("right"));
		still.setPlayMode(Animation.PlayMode.LOOP);
		left.setPlayMode(Animation.PlayMode.LOOP);
		right.setPlayMode(Animation.PlayMode.LOOP);
                
                player = new Player(still, left, right, (TiledMapTileLayer) map.getLayers().get(0));
		player.setPosition(11 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 14) * player.getCollisionLayer().getTileHeight());

                
               
		// Instantiation of the render for the map object
		renderer = new OrthogonalTiledMapRenderer(map);
                
                Gdx.input.setInputProcessor(player);
	}

    @Override
    public void render () {
            Gdx.gl.glClearColor(.5f, .7f, .9f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // Update the camera and render
            camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
            camera.update();

            renderer.setView(camera);

            renderer.render(background);

            renderer.getSpriteBatch().begin();
            player.draw(renderer.getSpriteBatch());
            renderer.getSpriteBatch().end();

            renderer.render(foreground);
            
    }    
    
    @Override
    public void dispose () {
            // Free resources
            manager.dispose();
    }
    

    

}