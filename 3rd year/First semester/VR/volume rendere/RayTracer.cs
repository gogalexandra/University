using ray_tracer;
using System;
using System.Drawing;
using System.IO;
using System.Runtime.InteropServices;
using System.Threading;

namespace rt
{
    class RayTracer
    {
        private Light[] lights;
        private double maxSamplingDistance = 1000;
        private double samplingStep = 1;
        private Asset asset;
    

        public RayTracer(Light[] lights, Asset asset)
        {
            this.lights = lights;
            this.asset = asset;
        }

        private double ImageToViewPlane(int n, int imgSize, double viewPlaneSize)
        {
            var u = n * viewPlaneSize / imgSize;
            u -= viewPlaneSize / 2;
            return u;
        }

        private bool IsLit(Vector point, Light light)
        {
            // ADD CODE HERE: Detect whether the given point has a clear line of sight to the given light
    
            return true;
        }

        public void Render(Camera camera, int width, int height, string filename)
        {
            var background = new Color(0, 0, 0, 1.0);
            var image = new Image(width, height);

            for (var i = 0; i < width; i++)
            {
                for (var j = 0; j < height; j++)
                {
                    // compute the ViewDir vector
                    Vector viewParallel = camera.Up ^ camera.Direction;
                    Vector x1 = camera.Position + camera.Direction * camera.ViewPlaneDistance
                        + camera.Up * ImageToViewPlane(j, height, camera.ViewPlaneHeight)
                        + viewParallel * ImageToViewPlane(i, width, camera.ViewPlaneWidth);
                    image.SetPixel(i, j, background);
                    Line tracedDirection = new Line(camera.Position, x1);

                    bool hasReachTheAsset = false;
                    double samplingStepCount = 1;
                    double currentAlpha = 0;
                    Color currentColor = new Color();
                    // sampling until maxidstance
                    while (samplingStepCount < maxSamplingDistance)
                {   
                        // sample with a step; coordinate to position returns position vector
                        //that is samplingStepCount distance away from the starting point of the line
                        Vector samplePosition = tracedDirection.CoordinateToPosition(samplingStepCount);

                        // check if we are in the 3d matrix with the sampling; until we reach it
                        //we do nothin
                        if (!asset.isPointInside(samplePosition))
                        {
                            samplingStepCount += samplingStep;
                            // check if the sample has already reached the asset
                            if (hasReachTheAsset)
                            {
                                // don't sample further
                                break;
                            }
                            else
                            {
                                // we haven't reached the asset yet
                                continue;
                            }
                        }
                        hasReachTheAsset = true;
                        double value = asset.getAssetValueFromPosition(samplePosition) / 255.0;
                        
                        //from values 0 to 19 the informatio will be the background itself; then we sample
                        if (value >= 0.0/255.0 && value < 19.0/255.0)
                        {
                            image.SetPixel(i, j, background);
                        }
                        else
                        {
                            currentAlpha += value;
                            if (currentAlpha >= 1.0)
                            {
                                currentAlpha = 1.0;
                            }

                            Color newColor = new Color(value, value, value, value);
                            Material material = new Material(
                                new Color(51.0/256.0, 51.0 / 256.0, 255.0/256.0, 1.0),
                                new Color(51.0 / 256.0, 51.0 / 256.0, 255.0/256.0, 1.0),
                                new Color(51.0 / 256.0, 51.0 / 256.0, 255.0/256.0, 1.0),
                                8
                            );
                            newColor *= 1.0 - currentAlpha;
                            //lighting; we grab all values from adjacent cubes and calculate normal

                            foreach (Light light in lights)
                            {
                                newColor += material.Ambient * light.Ambient;

                                double a = asset.getAssetValueFromPosition(samplePosition + new Vector(1, 0, 0));
                                double b = asset.getAssetValueFromPosition(samplePosition + new Vector(-1, 0, 0));
                                double c = asset.getAssetValueFromPosition(samplePosition + new Vector(0, 1, 0));
                                double d = asset.getAssetValueFromPosition(samplePosition + new Vector(0, -1, 0));
                                double e = asset.getAssetValueFromPosition(samplePosition + new Vector(0, 0, 1));
                                double f = asset.getAssetValueFromPosition(samplePosition + new Vector(0, 0, -1));

                                Vector N = new Vector(a - b, c - d, e - f).Normalize();
                                if (N * tracedDirection.Dx > 0)
                                {
                                    N *= -1.0;
                                }
                                Vector E = (camera.Position - samplePosition).Normalize();
                                Vector T = (light.Position - samplePosition).Normalize();
                                Vector R = (N * (N * T) * 2 - T).Normalize();
                                if (N * T > 0)
                                {
                                    newColor += material.Diffuse * light.Diffuse * (N * T);
                                }
                                if (E * R > 0)
                                {
                                    newColor += material.Specular * light.Specular *
                                        Math.Pow(E * R, material.Shininess);
                                }
                                newColor *= light.Intensity;
                            }

                            currentColor += newColor;

                            image.SetPixel(i, j, currentColor);
                            if (currentAlpha >= 1.0)
                            {
                                break;
                            }
                        }

                        samplingStepCount += samplingStep;
                    }
                }
                Console.WriteLine("i = " + i);
            }
            image.Store(filename);
        }
    }
}