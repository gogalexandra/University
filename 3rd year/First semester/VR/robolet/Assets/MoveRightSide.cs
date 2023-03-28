using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoveRightSide : MonoBehaviour
{
    public float velocity = 30.0f;
    public float maxAngle = 0.0f;
    public float minAngle = -45.0f;
    public Vector3 rotationDirection = new(-1, -1, 0);
    private float x;

    // Start is called before the first frame update
    void Start()
    {
        transform.Rotate(Vector3.left, 45.0f);
    }

    // Update is called once per frame
    void Update()
    {
        var xNew = x - velocity * Time.deltaTime;
        if (xNew > maxAngle || xNew < minAngle)
        {
            velocity = -velocity;
        }
        x -= velocity * Time.deltaTime;
        transform.localRotation = Quaternion.AngleAxis(x, rotationDirection);
    }

    private void OnValidate()
    {
    }

}
