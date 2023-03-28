using UnityEngine;
using System.Collections;

using System.Collections;
using System.Collections.Generic;
using UnityEngine;

 

public class SpiderMove : MonoBehaviour
{
	public GameObject targetObject;
 	private Vector3 targetObjectPos;
 	private float speed = 3.0f;

    // Start is called before the first frame update
    void Start(){}

    // Update is called once per frame
    void Update()
    {
		targetObjectPos = new Vector3(targetObject.transform.position.x, targetObject.transform.position.y, targetObject.transform.position.z);      
      	gameObject.transform.position = Vector3.MoveTowards(transform.position, targetObjectPos, speed * Time.deltaTime);
    }
}