package gameSystem.map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;



public class MappaMia extends ApplicationAdapter implements InputProcessor{
    private TiledMap map;
    private AssetManager manager;
    private int tileWidth, tileHeight,
	            mapWidthInTiles, mapHeightInTiles,
	            mapWidthInPixels, mapHeightInPixels;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    private final String mapFileName;
    SpriteBatch sb;
    Texture texture;
    Sprite sprite;

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
		Gdx.input.setInputProcessor(this);
                
                sb = new SpriteBatch();
                texture = new Texture(Gdx.files.internal("foggia.png"));
                sprite = new Sprite(texture);
                sprite.setPosition(mapWidthInPixels * .5f, mapHeightInPixels * .5f);
		// Instantiation of the render for the map object
		renderer = new OrthogonalTiledMapRenderer(map);
	}

    @Override
    public void render () {
            Gdx.gl.glClearColor(.5f, .7f, .9f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // Update the camera and render
            camera.update();
            renderer.setView(camera);
            renderer.render();
            sb.setProjectionMatrix(camera.combined);
            
            sb.begin();
            sprite.draw(sb);
            sb.end();
    }    
    
    @Override
    public void dispose () {
            // Free resources
            manager.dispose();
    }
    

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT){
            camera.translate(-32,0);
            sprite.translate(-32, 0);
        }
        if(keycode == Input.Keys.RIGHT){
            camera.translate(32,0);
            sprite.translate(32, 0);
        }   
        if(keycode == Input.Keys.UP){
            camera.translate(0,32);
            sprite.translate(0,32);
        }if(keycode == Input.Keys.DOWN){
            camera.translate(0,-32);
            sprite.translate(0,-32);
        }if(keycode == Input.Keys.NUM_1)
            map.getLayers().get(0).setVisible(!map.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            map.getLayers().get(1).setVisible(!map.getLayers().get(1).isVisible());
        if(keycode == Input.Keys.NUM_3)
            map.getLayers().get(2).setVisible(!map.getLayers().get(2).isVisible());
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}