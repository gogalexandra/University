using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LookAtButterfly : MonoBehaviour
{
    public GameObject butterfly;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        // unity look at mouse https://forum.unity.com/threads/how-do-i-look-at-the-mouse-position-3d.542838/ 
        var butterflyPosition = butterfly.transform.position;
        var lookAtPos = butterflyPosition;
        var transform1 = transform;
        var transformPosition = transform1.position;
        
        // lookAtPos.z += butterflyPosition.y - transformPosition.y;
        // lookAtPos = butterfly.ScreenToWorldPoint(lookAtPos);
        // transform1.right = lookAtPos - transformPosition;
        transform1.LookAt(butterfly.transform);
    }
}
