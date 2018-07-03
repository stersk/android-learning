package com.example.p1711_opengl_color;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.p1721_opengl_perspective.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glLineWidth;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.glViewport;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private Context context;
    private int programId;
    private FloatBuffer vertexData;
    private int uColorLocation;
    private int aColorLocation;
    private int aPositionLocation;

    public OpenGLRenderer(Context context) {
        this.context = context;
        //prepareDataTriangle();
        //prepareDataThreeLines();
        prepareDataGradientTriangle();
    }

// Simple colors
//    @Override
//    public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
//        glClearColor(0f, 0f, 0f, 1f);
//        int vertexShaderId = ShaderUtils.createShader(context, GL_VERTEX_SHADER, R.raw.triangle_vertex_shader);
//        int fragmentShaderId = ShaderUtils.createShader(context, GL_FRAGMENT_SHADER, R.raw.triangle_fragment_shader);
//        programId = ShaderUtils.createProgram(vertexShaderId, fragmentShaderId);
//        glUseProgram(programId);
//        bindDataTriangle();
//        bindDataGradient();
//    }

// Gradient colors
    @Override
    public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
        glClearColor(0f, 0f, 0f, 1f);
        int vertexShaderId = ShaderUtils.createShader(context, GL_VERTEX_SHADER, R.raw.gradient_vertex_shader);
        int fragmentShaderId = ShaderUtils.createShader(context, GL_FRAGMENT_SHADER, R.raw.gradient_fragment_shader);
        programId = ShaderUtils.createProgram(vertexShaderId, fragmentShaderId);
        glUseProgram(programId);
        bindDataGradient();
    }

    @Override
    public void onSurfaceChanged(GL10 arg0, int width, int height) {
        glViewport(0, 0, width, height);
    }

    private void prepareDataTriangle() {
        float[] vertices = {
                // треугольник 1
                -0.9f, 0.8f,
                -0.9f, 0.2f,
                -0.5f, 0.8f,

                // треугольник 2
                -0.6f, 0.2f,
                -0.2f, 0.2f,
                -0.2f, 0.8f,

                // треугольник 3
                0.1f, 0.8f,
                0.1f, 0.2f,
                0.5f, 0.8f,

                // треугольник 4
                0.1f, 0.2f,
                0.5f, 0.2f,
                0.5f, 0.8f,

                // линия 1
                -0.7f, -0.1f,
                0.7f, -0.1f,

                // линия 2
                -0.6f, -0.2f,
                0.6f, -0.2f,

                // точка 1
                -0.5f, -0.3f,

                // точка 2
                0.0f, -0.3f,

                // точка 3
                0.5f, -0.3f,
        };

        vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);
    }

    private void prepareDataThreeLines() {
        float[] vertices = {
                // линия 1
                -0.4f, 0.6f, 1.0f, 0.0f, 0.0f,
                0.4f, 0.6f, 0.0f, 1.0f, 0.0f,

                // линия 2
                0.6f, 0.4f, 0.0f, 0.0f, 1.0f,
                0.6f, -0.4f, 1.0f, 1.0f, 1.0f,

                // линия 3
                0.4f, -0.6f, 1.0f, 1.0f, 0.0f,
                -0.4f, -0.6f, 1.0f, 0.0f, 1.0f,
        };

        vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);
    }

    private void prepareDataGradientTriangle() {
        float[] vertices = {
                -0.5f, -0.2f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.2f, 0.0f, 1.0f, 0.0f,
                0.5f, -0.2f, 0.0f, 0.0f, 1.0f,
        };

        vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);
    }

    private void bindDataTriangle() {
        uColorLocation = glGetUniformLocation(programId, "u_Color");
        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
        aPositionLocation = glGetAttribLocation(programId, "a_Position");
        vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 0, vertexData);
        glEnableVertexAttribArray(aPositionLocation);
    }

    private void bindDataGradient() {
        // координаты
        aPositionLocation = glGetAttribLocation(programId, "a_Position");
        vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 20, vertexData);
        glEnableVertexAttribArray(aPositionLocation);

        // цвет
        aColorLocation = glGetAttribLocation(programId, "a_Color");
        vertexData.position(2);
        glVertexAttribPointer(aColorLocation, 3, GL_FLOAT, false, 20, vertexData);
        glEnableVertexAttribArray(aColorLocation);
    }

// Simple
//    @Override
//    public void onDrawFrame(GL10 arg0) {
//        glClear(GL_COLOR_BUFFER_BIT);
//        glLineWidth(5);
//
//        // синие треугольники
//        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
//        glDrawArrays(GL_TRIANGLES, 0, 12);
//
//        // зеленые линии
//        glUniform4f(uColorLocation, 0.0f, 1.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_LINES, 12, 4);
//
//        // красные точки
//        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_POINTS, 16, 3);
//    }

// Two lines with different color
//    public void onDrawFrame(GL10 arg0) {
//        glClear(GL_COLOR_BUFFER_BIT);
//        glLineWidth(5);
//
//        // синие треугольники
//        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
//        glDrawArrays(GL_TRIANGLES, 0, 12);
//
//        // зеленая линия
//        glUniform4f(uColorLocation, 0.0f, 1.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_LINES, 12, 2);
//
//        // желтая линия
//        glUniform4f(uColorLocation, 1.0f, 1.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_LINES, 14, 2);
//
//        // красные точки
//        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_POINTS, 16, 3);
//    }

//  Three lines with gradient color
//    public void onDrawFrame(GL10 arg0) {
//        glLineWidth(5);
//        glDrawArrays(GL_LINE_LOOP, 0, 6);
//    }

//  Triangle with gradient color
    public void onDrawFrame(GL10 arg0) {
        glLineWidth(5);
        glDrawArrays(GL_TRIANGLES, 0, 6);
    }
}
