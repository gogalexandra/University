using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShootAllTime : MonoBehaviour
{
    public Rigidbody projectile;
    public float speed = 15;
    float timer;
    int waitingTime = 1;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        timer += Time.deltaTime;
        if (timer > waitingTime)
        {
            Rigidbody p = Instantiate(projectile, transform.position, transform.rotation);
            p.velocity = -transform.right * speed;
            timer = 0;
        }
        
        
    }
}
