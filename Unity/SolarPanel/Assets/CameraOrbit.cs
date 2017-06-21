using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraOrbit : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update ()
    {
		if (Input.touchCount == 1)
        {
            Touch touch = Input.GetTouch(0);
            Vector2 screenCenter = new Vector2(Screen.width / 2, Screen.height / 2);
            Vector2 lastFramePos = touch.position - touch.deltaPosition;
            Vector2 firstVector = lastFramePos - screenCenter;
            Vector2 convertedMouseInput = new Vector2(touch.position.x, touch.position.y);
            Vector2 secondVector = convertedMouseInput - screenCenter;
            float angle = Vector2.Angle(firstVector, secondVector);
            Vector3 cross = Vector3.Cross(firstVector, secondVector);
            if (cross.z < 0)
            {
                angle = -angle;
            }
            var rotation = Quaternion.Euler(0, transform.rotation.eulerAngles.y + angle, 0);
            transform.rotation = rotation;
        }
    }
}
