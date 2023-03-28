using System;
using UnityEngine;

namespace Behaviours
{
    public class RightArm : MonoBehaviour
    {
        private float _speed = 30.0f;
        private const float MinRotation = 0.0f;
        private const float MaxRotation = 45.0f;
        // private const float MinRotation = -45.0f;
        // private const float MaxRotation = 0.0f;

        private float _currentRotation;

        // Update is called once per frame
        void Update()
        {
            var dt = Time.deltaTime;
            var x1 = _currentRotation + _speed*dt;
            if (x1 > MaxRotation)
                _speed = -1 * Math.Abs(_speed);
            else if (x1 < MinRotation)
                _speed = Math.Abs(_speed);
            _currentRotation += _speed*dt;
            transform.localRotation = Quaternion.AngleAxis(_currentRotation, Vector3.left);
        }
    }
}
