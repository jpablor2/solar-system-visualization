using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SetTexts : MonoBehaviour {
    private Text LeftText;
    private Text RightText;
    private string left;
    private string right;
    // Use this for initialization
    void Start () {
        LeftText = GameObject.Find("LeftText").GetComponent<Text>();
        RightText = GameObject.Find("RightText").GetComponent<Text>();
        left = "";
        right = "";
    }
	
	// Update is called once per frame
	void Update () {
        if (Input.GetKeyDown(KeyCode.Escape)) { Application.Quit(); }
        LeftText.text = left;
        RightText.text = right;
    }

    public void SetLeftText(string data)
    {
        left = data;
    }

    public void SetRightText(string data)
    {
        right = data;
    }
}
