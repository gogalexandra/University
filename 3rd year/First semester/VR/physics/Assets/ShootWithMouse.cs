using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShootWithMouse : MonoBehaviour
{

    [SerializeField] public Rigidbody p;
    private Camera _camera;
    // Start is called before the first frame update


    private void Start()
    {
        _camera = Camera.main;
    }
    
    // Update is called once per frame
    private void Update()
    {
        if (!Input.GetMouseButton(0)) return;
        var ray = _camera.ScreenPointToRay(Input.mousePosition);
        if (Physics.Raycast(ray, out var hit))
            FireAtPoint(hit.point);
    }

    private void FireAtPoint(Vector3 point)
    {
        // Rigidbody objPrefab = Resources.Load("Projectile") as Rigidbody;
        // Rigidbody p = Instantiate(objPrefab, transform.position, transform.rotation);
        //Rigidbody p = Instantiate(projectile, transform.position, transform.rotation);
        // var transformPosition = transform.position;
        // var velocity = (point - transformPosition)*5;
        // p.velocity = velocity;

        var transformPosition = transform.position;
        var velocity = (point - transformPosition)*5;
        Debug.Log(velocity);
        p.transform.position = transformPosition;
        p.velocity = velocity;
        
    }
}
