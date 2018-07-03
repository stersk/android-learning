package com.example.p1721_opengl_perspective;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_TEST;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.glViewport;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private Context context;
    private int programId;
    private FloatBuffer vertexData;
    private int uColorLocation;
    private int aPositionLocation;
    private int uMatrixLocation;
    private float[] mProjectionMatrix;

    public OpenGLRenderer(Context context) {
        this.context = context;
//        prepareDataPoint();
//        prepareDataPointPerspective();
//        prepareDataPointPerspectiveWithZ();
        prepareDataVerticesPerspectiveWithZ();

    }

    @Override
    public void onSurfaceCreated(GL10 arg0, EGLConfig arg1) {
        glClearColor(0f, 0f, 0f, 1f);
        glEnable(GL_DEPTH_TEST);
        int vertexShaderId = ShaderUtils.createShader(context, GL_VERTEX_SHADER, R.raw.perspective_vertex_shader);
        int fragmentShaderId = ShaderUtils.createShader(context, GL_FRAGMENT_SHADER, R.raw.triangle_fragment_shader);
        programId = ShaderUtils.createProgram(vertexShaderId, fragmentShaderId);
        glUseProgram(programId);
        bindData();
    }

    @Override
    public void onSurfaceChanged(GL10 arg0, int width, int height) {
        glViewport(0, 0, width, height);
        bindMatrix(width, height);
    }

    private void prepareDataPoint() {
        float x1 = -0.5f, y1 = -0.8f, x2 = 0.5f, y2 = -0.8f;
        float[] vertices = {
                x1, y1, 0.0f, 1.0f,
                x2, y2, 0.0f, 1.0f,
        };

        vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);
    }

    private void prepareDataPointPerspective() {

        float x1 = -0.5f, y1 = -0.8f, x2 = 0.5f, y2 = -0.8f;
        float[] vertices = {
                x1, y1, 0.0f, 1.0f,
                x1, y1, 0.0f, 1.5f,
                x1, y1, 0.0f, 2.0f,
                x1, y1, 0.0f, 2.5f,
                x1, y1, 0.0f, 3.0f,
                x1, y1, 0.0f, 3.5f,

                x2, y2, 0.0f, 1.0f,
                x2, y2, 0.0f, 1.5f,
                x2, y2, 0.0f, 2.0f,
                x2, y2, 0.0f, 2.5f,
                x2, y2, 0.0f, 3.0f,
                x2, y2, 0.0f, 3.5f,
        };

        vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);
    }

    private void prepareDataPointPerspectiveWithZ() {
        float x1 = -0.5f, y1 = -0.8f, x2 = 0.5f, y2 = -0.8f;
        float[] vertices = {
                x1, y1, -1.0f,
                x1, y1, -1.5f,
                x1, y1, -2.0f,
                x1, y1, -2.5f,
                x1, y1, -3.0f,
                x1, y1, -3.5f,

                x2, y2, -1.0f,
                x2, y2, -1.5f,
                x2, y2, -2.0f,
                x2, y2, -2.5f,
                x2, y2, -3.0f,
                x2, y2, -3.5f,
        };

        vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);
    }

    private void prepareDataVerticesPerspectiveWithZ() {
        float z1 = -3.0f, z2 = -1.10f;

        float[] vertices = {
                // первый треугольник
                -0.7f, -0.5f, z1,
                0.3f, -0.5f, z1,
                -0.2f, 0.3f, z1,

                // второй треугольник
                -0.3f, -0.4f, z2,
                0.7f, -0.4f, z2,
                0.2f, 0.4f, z2,
        };

        vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);
    }

    private void bindData() {
        uColorLocation = glGetUniformLocation(programId, "u_Color");
        glUniform4f(uColorLocation, 0.0f, 1.0f, 0.0f, 1.0f);
        aPositionLocation = glGetAttribLocation(programId, "a_Position");
        vertexData.position(0);
        //glVertexAttribPointer(aPositionLocation, 4, GL_FLOAT, false, 0, vertexData);
        glVertexAttribPointer(aPositionLocation, 3, GL_FLOAT, false, 0, vertexData);
        glEnableVertexAttribArray(aPositionLocation);

        uMatrixLocation = glGetUniformLocation(programId, "u_Matrix");
    }

    private void bindMatrix(int width, int height) {
        float left = -1.0f;
        float right = 1.0f;
        float bottom = -1.0f;
        float top = 1.0f;
        float near = 1.0f;
        float far = 8.0f;
        float ratio;

        if (width > height) {
            ratio = (float) width / height;
            left *= ratio;
            right *= ratio;
        } else {
            ratio = (float) height / width;
            bottom *= ratio;
            top *= ratio;
        }

        mProjectionMatrix = new float[16];

        //Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far); // with perspective in deep
        Matrix.orthoM(mProjectionMatrix, 0, left, right, bottom, top, near, far); // without perspective in deep
        glUniformMatrix4fv(uMatrixLocation, 1, false, mProjectionMatrix, 0);
    }

    // Two points
    //    @Override
    //    public void onDrawFrame(GL10 arg0) {
    //        glClear(GL_COLOR_BUFFER_BIT);
    //        glDrawArrays(GL_POINTS, 0, 2);
    //    }

    // Two points with perspective
    //    @Override
    //    public void onDrawFrame(GL10 arg0) {
    //        glClear(GL_COLOR_BUFFER_BIT);
    //        glDrawArrays(GL_POINTS, 0, 12);
    //    }

    // Two triangles with perspective
    @Override
    public void onDrawFrame(GL10 arg0) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // зеленый треугольник
        glUniform4f(uColorLocation, 0.0f, 1.0f, 0.0f, 1.0f);
        glDrawArrays(GL_TRIANGLES, 0, 3);

        // синий треугольник
        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
        glDrawArrays(GL_TRIANGLES, 3, 3);
    }
}
