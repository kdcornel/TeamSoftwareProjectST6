package game.states;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import game.handlers.GameStateManager;

public class Play extends GameState{
    private World world;
    private Box2DDebugRenderer b2dr;
    
    public Play (GameStateManager gsm) {
        super(gsm);
        
        world = new World(new Vector2(0, -9.81f), true);
        b2dr = new Box2DDebugRenderer();
        
        //create platform
        BodyDef bdef = new BodyDef();
        bdef.position.set(160, 120);
        bdef.type = BodyType.StaticBody;
        Body body = world.createBody(bdef);
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 5);
        
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        body.createFixture(fdef);
        
    }
    
    public void handleInput() {}
    
    public void update(float dt) {
        world.step(dt, 6, 2);
    }
    
    public void render() {
        b2dr.render(world, cam.combined);
    }
    
    public void dispose() {}
}
