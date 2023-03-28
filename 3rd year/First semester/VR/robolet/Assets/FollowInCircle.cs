using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Serialization;

public class FollowInCircle : MonoBehaviour
{
    public Transform target;

    public float rotationSpeed = 1;
    public float radius = 1;
    public float elevationOffset = 0;

    private float _angle;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void LateUpdate()
    {
        _angle += rotationSpeed * Time.deltaTime;
        var positionOffset = new Vector3(Mathf.Cos(_angle) * radius, elevationOffset, Mathf.Sin(_angle) * radius);
        transform.position = target.position + positionOffset;
    }
}
