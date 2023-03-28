using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoveFromKeyboard : MonoBehaviour
{
    public Rigidbody rb;
    public float horizontalInput;
    public float verticalInput;
    public float speed = 5f;
    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody>();
    }

    // Update is called once per frame
    void Update()
    {
        horizontalInput = Input.GetAxisRaw("Horizontal");
        verticalInput = Input.GetAxisRaw("Vertical");
    }

    private void FixedUpdate()
    {
        rb.AddForce(Vector3.right * (speed * verticalInput)); // x axis
        rb.AddForce(Vector3.back * (speed * horizontalInput)); // z axis
    }

    private void OnGUI()
    {
        GUI.Label(new Rect(10, 10, 100, 20), rb.transform.position.ToString());
    }
}

