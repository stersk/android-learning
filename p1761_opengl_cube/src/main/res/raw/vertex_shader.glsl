attribute vec4 a_Position;
varying vec3 v_Position;
uniform mat4 u_Matrix;

void main()
{
    v_Position = a_Position.xyz;
    gl_Position = u_Matrix * a_Position;
}