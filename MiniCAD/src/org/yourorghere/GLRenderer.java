package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {
    int rot1=0,rot2=0, segm=32;
    float r1 = 0.5f, r2 =0.3f, h = 0.7f;
    boolean face = false, wire = true;
    public void Cylinder(GL gl, float R, float R2, float H, int n, boolean Wire, boolean Face)
    {
        if (wire)
        {
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_LINE);
        }
        else
        {
            gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
        }
        
        float alpha, da;
        alpha =0;
        da = (float)(2*Math.PI/n);
        while(alpha<=2*Math.PI)
        {
            gl.glBegin(GL.GL_QUADS);
                if(wire)
                {
                    gl.glColor3f(1, 1, 1);
                }
                else
                {
                  gl.glColor3d(Math.abs(Math.cos(alpha)), Math.abs(Math.cos(alpha)),Math.abs(Math.cos(alpha)));  
                }                
                gl.glVertex3f((float)(R*Math.cos(alpha)), (float)(R*Math.sin(alpha)), 0);
                gl.glVertex3f((float)(R2*Math.cos(alpha)), (float)(R2*Math.sin(alpha)), H);
                if ((!face)&&(!wire))
                {
                gl.glColor3d(Math.abs(Math.cos(alpha+da)), Math.abs(Math.cos(alpha+da)),Math.abs(Math.cos(alpha+da)));
                }
                gl.glVertex3f((float)(R2*Math.cos(alpha+da)), (float)(R2*Math.sin(alpha+da)), H);
                gl.glVertex3f((float)(R*Math.cos(alpha+da)), (float)(R*Math.sin(alpha+da)), 0);
            gl.glEnd();
            alpha = alpha + da;
        }
    }
    
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        
        gl.glRotatef(rot2, 1,0,0);
        gl.glRotatef(rot1, 0,0,1);
        
        Cylinder(gl, r1, r2, h, segm, wire, face);

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

