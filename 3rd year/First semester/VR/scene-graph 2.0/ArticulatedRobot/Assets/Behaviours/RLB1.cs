using System;
using UnityEngine;

namespace Behaviours
{
    public class RLB1 : MonoBehaviour
    {
        private float _speed = 30.0f;
        private const float MinRotation = -60.0f;
        private const float MaxRotation = 0.0f;
        private int waitingTime = 4;
        private float timer;


        private float _currentRotation;

        // Update is called once per frame
        void Update()
        {
            var dt = Time.deltaTime;
            timer += Time.deltaTime;
            if (timer > waitingTime){
                var x1 = _currentRotation + _speed*dt;
                if (x1 > MaxRotation )
                    _speed = -1 * Math.Abs(_speed);
                else if (x1 < MinRotation)
                    _speed = Math.Abs(_speed);
                _currentRotation += _speed*dt;
                transform.localRotation = Quaternion.AngleAxis(_currentRotation, Vector3.left);
                timer = 0;
            }
            
        }
    }
}
